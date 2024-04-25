package com.example.Tienda.Service;

import com.example.Tienda.Entity.Proveedor;
import com.example.Tienda.database.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorProveedor {

    Conexion c = new Conexion(); // Instancia de la clase para la conexión a la base de datos.

    //Consultas SQL
    final String INSERT = "INSERT INTO tienda2.proveedor (name, lastName, telephone, mail, country, type) VALUE(?,?,?,?,?,?)";
    final String UPDATE = "UPDATE tienda2.proveedor SET name = ?, lastName = ?, telephone = ?, mail = ?, country = ?, type = ? WHERE ID = ?";
    final String DELETE = "DELETE FROM tienda2.proveedor WHERE ID= ?";
    final String GETALL = "SELECT * FROM tienda2.proveedor";
    final String GETONE = "SELECT * FROM tienda2.proveedor WHERE ID = ?";

    public void insert(Proveedor p) throws SQLException {
        Connection connection = c.getConnect(); //Obtiene la conexion a la base de datos
        PreparedStatement ps = connection.prepareStatement(INSERT); // Preparo la consulta SQL
        ps.setString(1, p.getName()); //Establece los paramentos en la consulta preparada
        ps.setString(2, p.getLastName());
        ps.setString(3, p.getTelephone());
        ps.setString(4, p.getMail());
        ps.setString(5, p.getCountry());
        ps.setString(6, p.getType());
        ps.executeUpdate();
        connection.close();
    }

    public void update(Proveedor p) throws SQLException {
        PreparedStatement ps;
        Connection connection = c.getConnect();

        ps = connection.prepareStatement(UPDATE);
        ps.setString(1, p.getName());
        ps.setString(2, p.getLastName());
        ps.setString(3, p.getTelephone());
        ps.setString(4, p.getMail());
        ps.setString(5, p.getCountry());
        ps.setString(6, p.getType());
        ps.setLong(7, p.getId());
        ps.executeUpdate();
        closeStat(ps);
        connection.close();

    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement psSelect = null;
        PreparedStatement psDelete = null;
        Connection connection = c.getConnect();
        ResultSet rs = null;
        try {
            psSelect = connection.prepareStatement(GETONE);
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery();

            if (!rs.next()) { //Verifica si se encontró algun registro
                return false; //Si no encuentra ninguna registro devuelve false
            }

            psDelete = connection.prepareStatement(DELETE);
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
            return true;
        } finally {
            closeRs(rs);
            closeStat(psDelete);
            closeStat(psSelect);
            connection.close();
        }
    }

    public List<Proveedor> getAll() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = c.getConnect();
        List<Proveedor> proveedores = new ArrayList<>();
    try {

        ps = connection.prepareStatement(GETALL);
        rs = ps.executeQuery();
        while (rs.next()) {
            proveedores.add(convert(rs));
        }
    }catch (SQLException e)
    {
        System.out.println("Error en sql" + e.getMessage());
    } finally {

        closeRs(rs);
        closeStat(ps);
    }


        return proveedores;
    }

    public Proveedor getById(int id) throws SQLException{
        PreparedStatement ps;
        ResultSet rs;
        Connection connection = c.getConnect();
        Proveedor proveedor = null;

        ps = connection.prepareStatement(GETONE);
        ps.setInt(1,id);
        rs = ps.executeQuery();
        if (rs.next()){
            proveedor = convert(rs);
        }
        closeRs(rs);
        closeStat(ps);

        return proveedor;
    }

    private void closeStat(PreparedStatement stat) throws SQLException {
        if (stat != null) {
            stat.close();
        }
    }

    private void closeRs(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    private Proveedor convert(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String lastName = rs.getString("lastName");
        String telephone = rs.getString("telephone");
        String mail = rs.getString("mail");
        String country = rs.getString("country");
        String type = rs.getString("type");

        Proveedor proveedor = new Proveedor(name, lastName, telephone, mail, country, type);
        proveedor.setId(rs.getInt("id"));
        return proveedor;
    }

}
