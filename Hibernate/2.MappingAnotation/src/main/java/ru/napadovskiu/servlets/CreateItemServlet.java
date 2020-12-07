package ru.napadovskiu.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.napadovskiu.entities.*;
import ru.napadovskiu.services.CreateItem;
import ru.napadovskiu.storage.ItemStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
public class CreateItemServlet extends HttpServlet {

    private final CreateItem createItem = CreateItem.getInstance();

    private static final long serialVersionUID = 1L;

    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";

    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB

    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB

    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    private final ConcurrentMap<String, String> fields = new ConcurrentHashMap<>();

    private final ItemStorage itemStorage = ItemStorage.getInstance();


    private void takeFoto(List<FileItem>  formItems) throws Exception {

        String uploadPath = getServletContext().getRealPath("")+ File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                    fields.put("foto", storeFile.getAbsolutePath());
                } else {
                    fields.put(item.getFieldName(),item.getString("UTF-8"));
                }
            }
        }

    }



    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/createItem.jsp");
        rd.forward(req, resp);
    }


    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = true;



        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);

        upload.setSizeMax(MAX_REQUEST_SIZE);


        try {
            List<FileItem> formItems = upload.parseRequest(req);
            takeFoto(formItems);
        } catch (Exception ex) {
            req.setAttribute("pathFile","");
        }

        Item newItem = createItem.createItem(fields.get("car_name"),fields.get("select_GearBox"),fields.get("select_Transmission"),fields.get("select_engine"),fields.get("foto"));
        if (newItem == null) {
            result = false;
        }

        if (result) {
            List<Item> items = itemStorage.getAll();
            req.setAttribute("advertisements", items);
            req.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(req, resp);
        }
   }
}
