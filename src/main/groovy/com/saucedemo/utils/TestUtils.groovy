package com.saucedemo.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Класс с утилитами для тестов
 */
class TestUtils {
    
    private static final Logger log = LoggerFactory.getLogger(TestUtils.class)
    
    /**
     * Генерация случайной строки заданной длины
     * @param length длина строки
     * @return случайная строка
     */
    static String generateRandomString(int length) {
        def chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        def random = new Random()
        def result = new StringBuilder()
        
        length.times {
            result << chars[random.nextInt(chars.size())]
        }
        
        return result.toString()
    }
    
    /**
     * Получение текущей даты и времени в формате строки
     * @param format формат даты (по умолчанию: yyyy-MM-dd_HH-mm-ss)
     * @return строка с датой и временем
     */
    static String getCurrentTimestamp(String format = "yyyy-MM-dd_HH-mm-ss") {
        def dateFormat = new java.text.SimpleDateFormat(format)
        return dateFormat.format(new Date())
    }
}