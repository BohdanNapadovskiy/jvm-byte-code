package ru.napadovskiyb;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArraySubdivisionTest {

    @Test
    public void whenTestAddReturnSizeArray() {
        ArraySubdivision arraySubdivision = new ArraySubdivision();

        arraySubdivision.addElement("K1\\SK1");
        arraySubdivision.addElement("K1\\SK2");
        arraySubdivision.addElement("K1\\SK1\\SSK2");
        arraySubdivision.addElement("K1\\SK1\\SSK1");
        arraySubdivision.addElement("K2");
        arraySubdivision.addElement("K2\\SK1\\SSK2");
        arraySubdivision.addElement("K2\\SK1\\SSK1");

        arraySubdivision.sortByIncrease();
        int a = 1;
    }

    @Test
    public void whenSortByIncreaseThanReturnSortArray() {
        ArraySubdivision arraySubdivision = new ArraySubdivision();
        arraySubdivision.addElement("K1\\SK2");
        arraySubdivision.addElement("K1\\SK1\\SSK1");
        arraySubdivision.addElement("K1\\SK1");
        arraySubdivision.addElement("K1\\SK2");
        arraySubdivision.addElement("K2");
        arraySubdivision.addElement("K2\\SK1\\SSK1");
        arraySubdivision.addElement("K2\\SK1\\SSK2");
        arraySubdivision.sortByDecrease();
        int a = 1;

//        Subdivision subdivision = new Subdivision("K3");
//        arraySubdivision.getArrayList().sort(subdivision.sortByIncrease());
    }
}