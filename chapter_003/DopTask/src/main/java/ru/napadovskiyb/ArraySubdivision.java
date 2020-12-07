package ru.napadovskiyb;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 */
public class ArraySubdivision {

    /**
     *
     */
    private ArrayList<String> arrayList;

    public ArrayList<String> getArrayList() {
        return this.arrayList;
    }

    /**
     *
     */
    public ArraySubdivision() {
        this.arrayList = new ArrayList<String>();

    }

    /**
     *
     * @return
     */
    private boolean checkRootElement(String code) {
        boolean result = false;
        for (String checkCode: this.arrayList) {
            if (checkCode.equals(code)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private ArrayList returnArrayCode(String code) {
        String[] result =  code.split("\\\\");

        ArrayList<String> resultArray = new ArrayList<>();

        for (String elem : result) {
            resultArray.add(elem);
        }

        return resultArray;

    }

    public void addElement(String code) {
        ArrayList<String> check =  returnArrayCode(code);
        String newCode = "";
        for (String tmpCode:check) {
            newCode = newCode  + tmpCode;
            if (!checkRootElement(newCode)) {
                //Subdivision subdivision = new Subdivision(newCode);
                this.arrayList.add(newCode);
            }
            newCode = newCode + "\\";
        }
    }

    public void sortByIncrease() {
        Collections.sort(this.arrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void sortByDecrease() {
        Collections.sort(this.arrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = o2.compareTo(o1);
                ArrayList<String> code1  = returnArrayCode(o1);
                ArrayList<String> code2  = returnArrayCode(o2);
                while (code1.iterator().hasNext() && code2.iterator().hasNext() && result == 0) {
                    result = code2.iterator().next().compareTo(code1.iterator().next());
                }
                if (code2.iterator().hasNext() && result == 0) {
                    result = -1;
                }
                return result;
            }
        });
    }
}
