package ru.napadovskiyb;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 *
 */
public class XmlParserTest {
    /**
     *
     */
    private final Order firstOrder = new Order(true, 50.0, 70, 1);

    /**
     *
     */
    private final Order secondOrder = new Order(false, 25.0, 40, 2);

    /**
     *
     */
    private final Order thirdOrder = new Order(false, 40.0, 80, 3);

    /**
     *
     */
   private OrderBook orderBook = new OrderBook();

    /**
     *
     */
    @Test
    public void whenAddOrderThenReturnMapSize() {

        final int checkElem = 3;
        this.orderBook.addOrder(this.firstOrder);
        this.orderBook.addOrder(this.secondOrder);
        this.orderBook.addOrder(this.thirdOrder);
        assertThat(this.orderBook.getUnsortedMap().size(), is(checkElem));
    }

    /**
     *
     */
    @Test
    public void whenDeleteOrderThenReturnSize() {
        final int checkElem = 2;
        this.orderBook.addOrder(this.firstOrder);
        this.orderBook.addOrder(this.secondOrder);
        this.orderBook.addOrder(this.thirdOrder);
        this.orderBook.deleteOrder(this.secondOrder.getIdOrder());
        assertThat(this.orderBook.getUnsortedMap().size(), is(checkElem));
    }


}