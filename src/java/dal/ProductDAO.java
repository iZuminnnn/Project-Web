/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Product;
import entity.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cauch
 */
public class ProductDAO extends BaseDAO<Product> {

    @Override
    public void add(Product model) {
        try {
            String sql = "INSERT INTO [dbo].[Product]\n"
                    + "           ([itemName]\n"
                    + "           ,[description]\n"
                    + "           ,[image]\n"
                    + "           ,[itemRate]\n"
                    + "           ,[price]\n"
                    + "           ,[itemStatus]\n"
                    + "           ,[menuID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setString(3, model.getImage());
            statement.setInt(4, model.getRate());
            statement.setDouble(5, model.getPrice());
            statement.setInt(6, model.getStatus());
            statement.setInt(7, model.getMenuID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Product model) {

        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [itemName] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[itemRate] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[itemStatus] = ?\n"
                    + "      ,[menuID] = ?\n"
                    + " WHERE itemID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setString(3, model.getImage());
            statement.setInt(4, model.getRate());
            statement.setDouble(5, model.getPrice());
            statement.setInt(6, model.getStatus());
            statement.setInt(7, model.getMenuID());
            statement.setInt(8, model.getID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(int ID) {
        try {
            String sql = "DELETE FROM [dbo].[Product]\n"
                    + " WHERE itemID = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public ArrayList<Product> list() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select * from product";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product s = new Product();
                s.setID(rs.getInt("itemID"));
                s.setName(rs.getString("itemName"));
                s.setDescription(rs.getString("description"));
                s.setImage(rs.getString("image"));
                s.setRate(rs.getInt("itemRate"));
                s.setPrice(rs.getDouble("price"));
                s.setStatus(rs.getInt("itemStatus"));
                s.setMenuID(rs.getInt("menuID"));
                products.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return products;

    }

    @Override
    public Product get(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Product> list(int ID) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select * from Product where itemID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product s = new Product();
                s.setID(rs.getInt("itemID"));
                s.setName(rs.getString("itemName"));
                s.setDescription(rs.getString("description"));
                s.setImage(rs.getString("image"));
                s.setRate(rs.getInt("itemRate"));
                s.setPrice(rs.getDouble("price"));
                s.setStatus(rs.getInt("itemStatus"));
                s.setMenuID(rs.getInt("menuID"));
                products.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return products;

    }

    public Product getProduct(int ID) {
        Product s = new Product();
        try {
            String sql = "SELECT [itemID]\n"
                    + "      ,[itemName]\n"
                    + "      ,[description]\n"
                    + "      ,[image]\n"
                    + "      ,[itemRate]\n"
                    + "      ,[price]\n"
                    + "      ,[itemStatus]\n"
                    + "      ,[menuID]\n"
                    + "  FROM [dbo].[Product]"
                    + "WHERE itemID = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                s.setID(rs.getInt("itemID"));
                s.setName(rs.getString("itemName"));
                s.setDescription(rs.getString("description"));
                s.setImage(rs.getString("image"));
                s.setRate(rs.getInt("itemRate"));
                s.setPrice(rs.getDouble("price"));
                s.setStatus(rs.getInt("itemStatus"));
                s.setMenuID(rs.getInt("menuID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return s;

    }

    public ArrayList<Product> listType(int ID) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "Select * from Product p INNER JOIN Group_Product gp "
                    + "ON p.menuID = gp.id \n"
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product s = new Product();
                s.setID(rs.getInt("itemID"));
                s.setName(rs.getString("itemName"));
                s.setDescription(rs.getString("description"));
                s.setImage(rs.getString("image"));
                s.setRate(rs.getInt("itemRate"));
                s.setPrice(rs.getDouble("price"));
                s.setStatus(rs.getInt("itemStatus"));
                s.setMenuID(rs.getInt("menuID"));
                Type t = new Type();
                t.setID(rs.getInt("id"));
                t.setName(rs.getString("group"));
                s.setTname(t);
                products.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return products;

    }

    public ArrayList<Type> listType() {
        ArrayList<Type> type = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[group]\n"
                    + "  FROM [dbo].[Group_Product]";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Type t = new Type();
                t.setID(rs.getInt("id"));
                t.setName(rs.getString("group"));

                type.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return type;

    }

    public ArrayList<Product> search(String name, int pageindex, int totalpage) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "  (SELECT ROW_NUMBER() OVER (ORDER BY itemid ASC) as row_index ,*  FROM Product WHERE itemName LIKE '%'+?+'%') tbl\n"
                    + " WHERE  \n"
                    + " row_index >= (?-1) * ? + 1 \n"
                    + " AND \n"
                    + " row_index <=  ? * ?\n"
                   ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, pageindex);
            statement.setInt(3, totalpage);
            statement.setInt(4, pageindex);
            statement.setInt(5, totalpage);
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product s = new Product();
                s.setID(rs.getInt("itemID"));
                s.setName(rs.getString("itemName"));
                s.setDescription(rs.getString("description"));
                s.setImage(rs.getString("image"));
                s.setRate(rs.getInt("itemRate"));
                s.setPrice(rs.getDouble("price"));
                s.setStatus(rs.getInt("itemStatus"));
                s.setMenuID(rs.getInt("menuID"));
                products.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return products;

    }
     public int countSearch(String name) {
        String sql = "Select COUNT (*) as total from Product WHERE itemName LIKE '%'+?+'%'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int count() {
        String sql = "Select COUNT (*) as total from Product";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
       public int countType(int type) {
        String sql = "Select COUNT (*) as total from Product Where menuID = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, type);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public ArrayList<Product> list(int pageindex, int totalpage) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY itemid ASC) as row_index ,*  FROM Product) tbl\n"
                    + "WHERE  \n"
                    + "row_index >= (?-1) * ? + 1 \n"
                    + "AND\n"
                    + "row_index <=  ? * ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pageindex);
            statement.setInt(2, totalpage);
            statement.setInt(3, pageindex);
            statement.setInt(4, totalpage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product s = new Product();
                s.setID(rs.getInt("itemID"));
                s.setName(rs.getString("itemName"));
                s.setDescription(rs.getString("description"));
                s.setImage(rs.getString("image"));
                s.setRate(rs.getInt("itemRate"));
                s.setPrice(rs.getDouble("price"));
                s.setStatus(rs.getInt("itemStatus"));
                s.setMenuID(rs.getInt("menuID"));
                products.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return products;

    }
}
