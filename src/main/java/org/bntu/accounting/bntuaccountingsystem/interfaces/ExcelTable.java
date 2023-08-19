package org.bntu.accounting.bntuaccountingsystem.interfaces;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
     * @param sheet - Excel лист, на котором колонка будет создаваться
     * */
    void createColumn(int startRow, int endRow, int startColumn, int endColumn,
                      String title, CellStyle style, Sheet sheet);

    /**
     * Задаётся ширина ключевых столбцов таблицы
     * @param sheet - Excel лист
     * */
    void setAllColumnsWidth(Sheet sheet);

    /**
     * Добавляет всех учителей из списка в Excel таблицу.
     * @param teacherList - список учителей для добавления в таблицу
     * @param sheet - Excel лист
     * @return индекс строки, в которую был добавлен последний учитель
     * */
    int addAllTeacherToTable(List<Teacher> teacherList, Sheet sheet);

    /**
     * Добавляет учителя в заданную строку.
     * @param teacher - учитель
     * @param row - строка, в которую учитель будет добавляться
     * @return индекс строки, в которую был добавлен учитель
     * */
    int addOneTeacherToTable(Teacher teacher, Row row);



}
