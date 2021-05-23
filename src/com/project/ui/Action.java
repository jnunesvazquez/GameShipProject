package com.project.ui;

import java.sql.SQLException;

/**
 * Interfaz para definir acciones
 */
public interface Action {

    /**
     * Metodo que nos dira que accion debe realizar un objeto
     */
    void doAction() throws SQLException;
}
