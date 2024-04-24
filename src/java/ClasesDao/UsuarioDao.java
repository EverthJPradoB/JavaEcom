/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDao;

import Clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEYSON
 */
public class UsuarioDao {

    private static final String SELECT_FROM_USER_EMAIL_PASS = "SELECT * FROM usuario WHERE correo = ? && contraseña = ?";

    private Connection con;

    private String query;

    private PreparedStatement stmt;

    private ResultSet rs;

    public UsuarioDao(Connection con) {

        this.con = con;

    }

    public Usuario validarLogin(String correo, String contraseña) {
        Usuario objUsuario = null;
        try {

            stmt = this.con.prepareStatement(SELECT_FROM_USER_EMAIL_PASS);
            stmt.setString(1, correo);
            stmt.setString(2, contraseña);

            rs = stmt.executeQuery();

            while (rs.next()) {
                objUsuario = new Usuario();

                objUsuario.setIdUsuario(rs.getInt("id_usuario"));
                objUsuario.setNombre(rs.getString("nombre"));
                objUsuario.setApellido(rs.getString("apellido"));
                objUsuario.setTelefono(rs.getString("telefono"));
                objUsuario.setCorreo(rs.getString("correo"));
                objUsuario.setContraseña(rs.getString("contraseña"));
                objUsuario.setTipo(rs.getInt("tipo"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return objUsuario;

    }

    public List<Usuario> obtenerUsuarios() {

        List<Usuario> listaUsuario = new ArrayList<>();

        try {

            stmt = this.con.prepareStatement("SELECT * FROM `usuario`");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario objUsuario = new Usuario();

                objUsuario.setIdUsuario(rs.getInt("id_usuario"));
                objUsuario.setNombre(rs.getString("nombre"));
                objUsuario.setApellido(rs.getString("apellido"));
                objUsuario.setTelefono(rs.getString("telefono"));
                objUsuario.setCorreo(rs.getString("correo"));
                objUsuario.setContraseña(rs.getString("contraseña"));
                objUsuario.setTipo(rs.getInt("tipo"));
                listaUsuario.add(objUsuario);

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return listaUsuario;

    }

    public int insertUSer(Usuario objUsuario) {

        int com = 0;

        try {
            stmt = this.con.prepareStatement("INSERT INTO `usuario`( `nombre`, `apellido`, `telefono`, `correo`, `contraseña`, `tipo`) "
                    + "VALUES ( ? , ? , ?  , ?  , ?   , ? )");

            stmt.setString(1, objUsuario.getNombre());
            stmt.setString(2, objUsuario.getApellido());
            stmt.setString(3, objUsuario.getTelefono());
            stmt.setString(4, objUsuario.getCorreo());
            stmt.setString(5, objUsuario.getContraseña());
            stmt.setInt(6, objUsuario.getTipo());
            com = stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return com;
    }

    public Usuario obtenerUsuario(int id) {

        Usuario objUsuario = null;
        try {
            query = "select * from usuario where id_usuario = ? ";

            stmt = this.con.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                objUsuario = new Usuario();
                objUsuario.setIdUsuario(rs.getInt("id_usuario"));
                objUsuario.setNombre(rs.getString("nombre"));
                objUsuario.setApellido(rs.getString("apellido"));

                objUsuario.setTelefono(rs.getString("telefono"));
                objUsuario.setCorreo(rs.getString("correo"));
                objUsuario.setContraseña(rs.getString("contraseña"));
                objUsuario.setTipo(rs.getInt("tipo"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return objUsuario;

    }

    public boolean actualizarUsuario(Usuario objUsuario) {

        boolean estado = false;

        try {

            stmt = this.con.prepareStatement("UPDATE usuario SET nombre = ?  WHERE id_usuario = ?  ");

            stmt.setString(1, objUsuario.getNombre());
            /*stmt.setString(2, objUsuario.getApellido());
            stmt.setString(3, objUsuario.getTelefono());

            stmt.setString(4, objUsuario.getCorreo());
            stmt.setString(5, objUsuario.getContraseña());
            //   stmt.setInt(6, objUsuario.getTipo());*/

            stmt.setInt(2, objUsuario.getIdUsuario());

            stmt.executeUpdate();

            estado = true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return estado;

    }

    public boolean eliminarUsuario(int id) {
        boolean estado = false;

        try {

            stmt = this.con.prepareStatement("DELETE FROM `usuario` WHERE id_usuario = ?");

            stmt.setInt(1, id);

            stmt.executeUpdate();

            estado = true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return estado;

    }

}
