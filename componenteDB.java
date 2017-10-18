import java.sql.*;
import java.text.DateFormat;

public class componenteDB {
    // Atributos:
    private Connection icn_c;
    private PreparedStatement ips_ps = null;
    public ResultSet irs_r;

    private String is_DB, is_conexion, is_usuario, is_password, is_jdbc;

    // Metodos:
    public componenteDB(String db, String usr, String pwd) {
        is_DB = db;
        is_conexion = "jdbc:mysql://127.0.0.1/" + is_DB + "?relaxAutoCommit=true";
        is_usuario = usr;
        is_password = pwd;
        is_jdbc = "com.mysql.jdbc.Driver";
    }
    
   public String conectar() {
        try{
          Class.forName(is_jdbc);
          icn_c = DriverManager.getConnection(is_conexion,is_usuario , is_password);
          return "";
        } catch(Exception e) {
          System.out.println("ERROR: (of_conectar)" + e.getMessage());
          return "ERROR (conectar) " + e.getMessage();
        }
    }
    
    public String guardar_item(String a_tabla, String [] a_nomCol, String [] a_dato,
            int a_nd, boolean hacercommit) {
       String ls_dato, ls_stm = "", ls_where = "";
       int i;
       try{
          ls_where =  " WHERE " + a_nomCol[0] + " = '" + a_dato[0] + "'";
          ls_stm = "SELECT COUNT(*) FROM " + a_tabla + ls_where;
          ls_dato = obt_val_sql(ls_stm, 2, 1);
          if (Integer.parseInt(ls_dato) > 0) { // Existe el registro, actualizar si es necesario:
              ls_stm = "UPDATE " +  a_tabla + " SET " + a_nomCol[1] + "='" + a_dato[1].toUpperCase() + "'";
              for (i = 2; i < a_nd; i ++)
                  ls_stm +=", " + a_nomCol[i] + "='" + a_dato[i].toUpperCase() + "'";
              ls_stm += ls_where;
              ips_ps= icn_c.prepareStatement(ls_stm);
              ips_ps.executeUpdate();
          }
          else {
            ls_stm = "INSERT INTO " + a_tabla + "(" + a_nomCol[0];
            for (i = 1; i < a_nd; i ++)
               ls_stm += ", " + a_nomCol[i];
            ls_stm += ") VALUES (?";
            for (i = 1; i < a_nd; i ++)
               ls_stm += ", ?";
            ls_stm += ")";
            ips_ps = icn_c.prepareStatement(ls_stm);
            for (i = 0; i < a_nd; i ++)
               ips_ps.setString( i + 1, a_dato[i].toUpperCase());
            ips_ps.executeUpdate();
          }
          if (hacercommit) icn_c.commit();
          return "";
       } catch(Exception e) {
           return "Error en obt_val_sql: " + ls_stm + e.getMessage() + "\r\n";
       }
    }
 
    public String eliminar_item(String a_tabla, String a_nomCol, String a_dato, boolean consultar, boolean hacercommit) {
       String ls_dato, ls_stm = "", ls_where = "";
       int cuenta;
       if (a_dato == null) return "-1";
       try{
          ls_where =  " WHERE " + a_nomCol + " = '" + a_dato + "'";
          ls_stm = "SELECT COUNT(*) FROM " + a_tabla + ls_where;
          ls_dato = obt_val_sql(ls_stm, 2, 1);
          cuenta = Integer.parseInt(ls_dato);
          if (consultar)
              if (cuenta > 0) return "";
              else return "-1";
          if (cuenta > 0) { // Existe el registro, eliminarloo:
            ls_stm = "DELETE FROM "  + a_tabla + ls_where;
            ips_ps= icn_c.prepareStatement(ls_stm);
            ips_ps.executeUpdate();
          }
          if (hacercommit) icn_c.commit();
          return "";
       } catch(Exception e) {
           return "Error en of_obt_val_sql: " + ls_stm + e.getMessage() + "\r\n";
       }
    }

    public String obt_val_sql(String a_sql, int a_tipo, int a_ind) {
        int li_i;
        try{
          irs_r = icn_c.prepareStatement(a_sql).executeQuery();
          if (a_ind == -1)
              return "";
          for (li_i = 0; li_i < a_ind; li_i ++)
            if(!irs_r.next()) return null;
          if (a_tipo == 1)
            return irs_r.getString(1) + "";
          else if (a_tipo == 2)
            return irs_r.getInt(1) + "";
          else if (a_tipo == 3)
            return irs_r.getDouble(1) + "";
          return irs_r.getDate(1) + "";
        } catch(Exception e) {
       //   System.out.println("ERROR: (obt_val_sql)" + e.getMessage());
          return "ERROR (of_obt_val_sql) " + e.getMessage();
        }
    }
}
