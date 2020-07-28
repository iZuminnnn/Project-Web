/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Account;
import entity.Functionality;
import entity.Group;
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
public class AccountDAO extends BaseDAO<Account>{

    @Override
    public void add(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Account> list() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT accountid FROM Account";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountid(rs.getString("accountid"));
                accounts.add(account);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return accounts; 
    }

    @Override
    public Account get(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Account get(String accountid, String password) {
        Account account = null;
        try {
            String sql = "SELECT a.accountid,g.groupid,g.groupname,f.functionid,"
                    + "f.url FROM \n"
                    + "Account a INNER JOIN Group_Account ga\n"
                    + "ON a.accountid = ga.accountid\n"
                    + "INNER JOIN [Group] g ON g.groupid = ga.groupid\n"
                    + "INNER JOIN [Group_Functionality] gf ON gf.groupid = g.groupid\n"
                    + "INNER JOIN Functionality f ON gf.functionid = f.functionid\n"
                    + "WHERE a.accountid = ? AND a.[password] = ? "
                    + "ORDER BY g.groupid";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountid);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            Group currentGroup = new Group();
            
            currentGroup.setID(-1);
            while (rs.next()) {
                if (account == null) {
                    account = new Account();
                    account.setAccountid(rs.getString("accountid"));
                }
                if (currentGroup.getID() != rs.getInt("groupid")) {
                    currentGroup = new Group();
                    currentGroup.setID(rs.getInt("groupid"));
                    currentGroup.setName(rs.getString("groupname"));
                    account.getGroups().add(currentGroup);
                }
                Functionality function = new Functionality();
                function.setID(rs.getInt("functionid"));
                function.setUrl(rs.getString("url"));
                currentGroup.getFuntions().add(function);
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }
public void updateRole(ArrayList<Account> accounts) {
        try {
            connection.setAutoCommit(false);
            String delete_role = "DELETE Group_Account";
            PreparedStatement delete_role_stm
                    = connection.prepareStatement(delete_role);
            delete_role_stm.executeUpdate();
            for (Account account : accounts) {
                for (Group g : account.getGroups()) {
                    String add_role = "INSERT INTO [Group_Account]\n"
                            + "           ([accountid]\n"
                            + "           ,[groupid])\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?)";
                    PreparedStatement add_role_stm
                            = connection.prepareStatement(add_role);
                    add_role_stm.setString(1, account.getAccountid());
                    add_role_stm.setInt(2, g.getID());
                    add_role_stm.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateRole(Account account) {
        try {
            connection.setAutoCommit(false);
            String delete_role = "DELETE Group_Account WHERE accountid = ?";
            PreparedStatement delete_role_stm
                    = connection.prepareStatement(delete_role);
            delete_role_stm.setString(1, account.getAccountid());
            delete_role_stm.executeUpdate();

            for (Group g : account.getGroups()) {
                String add_role = "INSERT INTO [Group_Account]\n"
                        + "           ([accountid]\n"
                        + "           ,[groupid])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?)";
                PreparedStatement add_role_stm = 
                        connection.prepareStatement(add_role);
                add_role_stm.setString(1, account.getAccountid());
                add_role_stm.setInt(2, g.getID());
                add_role_stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public Account getUserByName (String name){
        try {
            String sql = "select * from [User] where accountid = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setAccountid(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                return account;
            }
        } catch (SQLException ex) {
          Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
