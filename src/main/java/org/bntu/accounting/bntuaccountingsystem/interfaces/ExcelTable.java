package org.bntu.accounting.bntuaccountingsystem.interfaces;

import org.apache.poi.ss.usermodel.*;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.json.JSONObject;

import java.util.List;

public interface ExcelTable {
    /**
     * Создаёт объеденённую, по заданным параметрам, ячейку,
     * представляющую колонку Excel таблицы.
     *
     * @param startRow - индекс строки, с которой начинается колонка
     * @param endRow - индекс строки, на которой колонка заканчивается
     * @param startColumn- индекс столбца, с которого начинается колонка
     * @param endColumn- индекс столбца, на котором колонка заканчивается
     * @param title - заголовок колонки
     * @param style - стиль колонки
     * */
    void createColumn(int startRow, int endRow, int startColumn, int endColumn,
                      String title, CellStyle style, boolean rotate);

    /**
     * Задаётся ширина ключевых столбцов таблицы
     * */
    void setAllColumnsWidth();

    /**
     * Добавляет всех учителей из списка в Excel таблицу.
     * @param startRow - индекс строки, с которой начнётся добавление учителей
     * @param teacherList - список учителей для добавления в таблицу
     * @return индекс строки, в которую был добавлен последний учитель
     * */
    int addAllTeacherToTable(int startRow, List<Teacher> teacherList);

    /**
     * Добавляет учителя в заданную строку.
     * @param number - номер учителя в таблице
     * @param teacher - учитель
     * @param row - строка, в которую учитель будет добавляться
     * */
    void addOneTeacherToTable(Integer number, Teacher teacher, Row row);



}
