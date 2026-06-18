package com.example.practica;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TareaDao {
    @Insert
    void insertar(Tarea tarea);

    @Query("SELECT * FROM tareas ORDER BY id DESC")
    List<Tarea> obtenerTodas();

    @Delete
    void eliminar(Tarea tarea);
}
