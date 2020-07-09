/*
 * DynamicTable.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package dynamicTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DynamicTable {

    protected List<String> columnNameList;
    protected List<Integer> columnWidthList;
    protected Comparator<String> stringLengthComp = Comparator.comparingInt(String::length);

    DynamicTable(String... columnNames) {

        columnNameList = new ArrayList<>();
        columnWidthList = new ArrayList<>();

        columnNameList.addAll(Arrays.asList(columnNames));

        columnWidthList.addAll(Arrays.stream(columnNames).map(String::length).collect(Collectors.toList()));
    }

    protected abstract boolean computeColumnWidthList();

    public abstract void printTable();

    protected void printDashLine() {

        final StringBuilder dashLine = new StringBuilder("+");

        for (final Integer width : columnWidthList) {

            for (int i = 0; i < (width + 2); i++) {

                dashLine.append("-");
            }

            dashLine.append("+");
        }

        System.out.println(dashLine);
    }

    protected void printHeaderForAccountStatement() {

        printDashLine();

        final StringBuilder header = new StringBuilder();

        for (int i = 0; i < columnNameList.size(); i++) {

            header.append(String.format("| %-" + columnWidthList.get(i) + "s ", columnNameList.get(i)));
        }

        header.append("|");

        System.out.println(header);
    }
}
