
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * UserModel class provides database operations for User records.
 * It supports adding, updating, searching, authentication,
 * password management and user registration.
 *
 * @author Abhay Malve
 * @version 1.0
 */

public class UserModel extends BaseModel<UserBean> {

    private static final Logger log = Logger.getLogger(UserModel.class);

    /**
     * Adds a new user record into the database.
     */
    @Override
    public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {

        log.debug("add() method started");

        Connection conn = null;
        int pk = 0;

        UserBean existBean = findByLogin(bean.getLogin());

        if (existBean != null) {
            log.warn("User login already exists");
            throw new DuplicateRecordException("login already exist");
        }

        try {

            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstmt.setLong(1, pk);
            pstmt.setString(2, bean.getFirstName());
            pstmt.setString(3, bean.getLastName());
            pstmt.setString(4, bean.getLogin());
            pstmt.setString(5, bean.getPassword());
            pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(7, bean.getMobileNo());
            pstmt.setLong(8, bean.getRoleId());
            pstmt.setInt(9, bean.getUnsuccessfulLogin());
            pstmt.setString(10, bean.getGender());
            pstmt.setTimestamp(11, bean.getLastLogin());
            pstmt.setString(12, bean.getUserLock());
            pstmt.setString(13, bean.getRegisteredIp());
            pstmt.setString(14, bean.getLastLoginIp());
            pstmt.setString(15, bean.getCreatedBy());
            pstmt.setString(16, bean.getModifiedBy());
            pstmt.setTimestamp(17, bean.getCreatedDatetime());
            pstmt.setTimestamp(18, bean.getModifiedDatetime());

            pstmt.executeUpdate();
            conn.commit();

            System.out.println("record inserted successfully");
            log.info("User record inserted successfully");

        } catch (Exception e) {

            log.error("Error while inserting user record", e);
            e.printStackTrace();
            JDBCDataSource.trnRollBack(conn);

        } finally {

            JDBCDataSource.closeConnection(conn);
        }

        return pk;
    }

    /**
     * Updates an existing user record in the database.
     */
    @Override
    public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

        log.debug("update() method started");

        Connection conn = null;

        UserBean existBean = findByLogin(bean.getLogin());

        if (existBean != null && existBean.getId() != bean.getId()) {
            log.warn("User login already exists");
            throw new DuplicateRecordException("login already exist");
        }

        try {

            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "update " + getTable()
                    + " set first_name = ?, last_name = ?, login = ?, password = ?, dob = ?, mobile_no = ?, role_id = ?, unsuccessful_login = ?, gender = ?, last_login = ?, user_lock = ?, registered_ip = ?, last_login_ip = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

            pstmt.setString(1, bean.getFirstName());
            pstmt.setString(2, bean.getLastName());
            pstmt.setString(3, bean.getLogin());
            pstmt.setString(4, bean.getPassword());
            pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(6, bean.getMobileNo());
            pstmt.setLong(7, bean.getRoleId());
            pstmt.setInt(8, bean.getUnsuccessfulLogin());
            pstmt.setString(9, bean.getGender());
            pstmt.setTimestamp(10, bean.getLastLogin());
            pstmt.setString(11, bean.getUserLock());
            pstmt.setString(12, bean.getRegisteredIp());
            pstmt.setString(13, bean.getLastLoginIp());
            pstmt.setString(14, bean.getCreatedBy());
            pstmt.setString(15, bean.getModifiedBy());
            pstmt.setTimestamp(16, bean.getCreatedDatetime());
            pstmt.setTimestamp(17, bean.getModifiedDatetime());
            pstmt.setLong(18, bean.getId());

            pstmt.executeUpdate();
            conn.commit();

            System.out.println("record updated successfully");
            log.info("User record updated successfully");

        } catch (Exception e) {

            log.error("Error while updating user record", e);
            e.printStackTrace();
            JDBCDataSource.trnRollBack(conn);

        } finally {

            JDBCDataSource.closeConnection(conn);
        }
    }

    /**
     * Generates a WHERE clause based on the provided user details.
     */
    @Override
    public String getWhereClause(UserBean bean) {

        log.debug("getWhereClause() method started");

        StringBuffer sql = new StringBuffer("");

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }

            if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
                sql.append(" and first_name like '" + bean.getFirstName() + "%'");
            }

            if (bean.getLastName() != null && bean.getLastName().length() > 0) {
                sql.append(" and last_name like '" + bean.getLastName() + "%'");
            }

            if (bean.getLogin() != null && bean.getLogin().length() > 0) {
                sql.append(" and login like '" + bean.getLogin() + "%'");
            }

            if (bean.getDob() != null && bean.getDob().getTime() > 0) {
                sql.append(" and dob like '" + bean.getDob() + "%'");
            }

            if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
                sql.append(" and mobile_no = " + bean.getMobileNo() + "%'");
            }

            if (bean.getRoleId() > 0) {
                sql.append(" and role_id = " + bean.getRoleId());
            }

            if (bean.getUnsuccessfulLogin() > 0) {
                sql.append(" and unsuccessful_login = " + bean.getUnsuccessfulLogin());
            }

            if (bean.getGender() != null && bean.getGender().length() > 0) {
                sql.append(" and gender like '" + bean.getGender() + "%'");
            }

            if (bean.getLastLogin() != null) {
                sql.append(" and last_login = " + bean.getLastLoginIp());
            }

            if (bean.getUserLock() != null && bean.getUserLock().length() > 0) {
                sql.append(" and user_lock like '" + bean.getUserLock() + "%'");
            }

            if (bean.getRegisteredIp() != null && bean.getRegisteredIp().length() > 0) {
                sql.append(" and registered_ip like '" + bean.getRegisteredIp() + "%'");
            }

            if (bean.getLastLoginIp() != null && bean.getLastLoginIp().length() > 0) {
                sql.append(" and last_login_ip like '" + bean.getLastLoginIp() + "%'");
            }

            if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
                sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");
            }

            if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
                sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");
            }

            if (bean.getCreatedDatetime() != null) {
                sql.append(" and created_datetime = '" + bean.getCreatedDatetime() + "'");
            }

            if (bean.getModifiedDatetime() != null) {
                sql.append(" and modified_datetime = '" + bean.getModifiedDatetime() + "");
            }
        }

        log.debug("getWhereClause() method completed");

        return sql.toString();
    }

    /**
     * Finds a user record using the login ID.
     */
    public UserBean findByLogin(String login) {

        log.debug("findByLogin() method started");

        UserBean bean = findByUniqueColumn("login", login);

        if (bean != null) {
            log.info("User found by login");
        } else {
            log.warn("User not found by login");
        }

        return bean;
    }

    /**
     * Authenticates a user using login ID and password.
     */
    public UserBean authenticate(String login, String password) {

        log.debug("authenticate() method started");

        UserBean bean = findByLogin(login);

        if (bean != null && bean.getPassword().equals(password)) {
            log.info("User authentication successful");
            return bean;
        }

        log.warn("User authentication failed");
        return null;
    }

    /**
     * Changes the password of a user after verifying the old password.
     */
    public UserBean changePassword(String newPassword, String oldPassword, String login) {

        log.debug("changePassword() method started");

        UserBean bean = findByLogin(login);

        if (bean != null && bean.getPassword().equals(oldPassword)) {

            bean.setPassword(newPassword);
            update(bean);

            HashMap<String, String> map = new HashMap<String, String>();
            EmailMessage msg = new EmailMessage();

            map.put("login", bean.getLogin());
            map.put("password", bean.getPassword());
            map.put("firstName", bean.getFirstName());
            map.put("lastName", bean.getLastName());

            msg.setTo(map.get("login"));
            msg.setSubject("password changed");
            msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
            msg.setMessageType(EmailMessage.HTML_MSG);

            EmailUtility.sendMail(msg);

            log.info("User password changed successfully");

            return bean;
        }

        log.warn("Password change failed: Invalid old password");
        return null;
    }

    /**
     * Retrieves a user's password recovery information.
     */
    public UserBean forgotPassword(String login) {

        log.debug("forgotPassword() method started");

        UserBean bean = findByLogin(login);

        if (bean != null) {

            HashMap<String, String> map = new HashMap<String, String>();
            EmailMessage msg = new EmailMessage();

            map.put("login", bean.getLogin());
            map.put("password", bean.getPassword());
            map.put("firstName", bean.getFirstName());
            map.put("lastName", bean.getLastName());

            msg.setTo(map.get("login"));
            msg.setSubject("Password Changed");
            msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
            msg.setMessageType(EmailMessage.HTML_MSG);

            EmailUtility.sendMail(msg);

            log.info("Forgot password email sent successfully");

            return bean;
        }

        log.warn("Forgot password failed: User not found");
        return null;
    }

    /**
     * Registers a new user and sends registration details through email.
     */
    public long register(UserBean bean) {

        log.debug("register() method started");

        long pk = add(bean);

        HashMap<String, String> map = new HashMap<String, String>();
        EmailMessage msg = new EmailMessage();

        map.put("login", bean.getLogin());
        map.put("password", bean.getPassword());

        msg.setTo(map.get("login"));
        msg.setSubject("User Registration Information");
        msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        System.out.println("mail send successfully");

        log.info("User registration completed successfully");

        return pk;
    }

    /**
     * Returns the database table name for User records.
     */
    @Override
    public String getTable() {

        log.debug("getTable() method called");

        return "st_user";
    }

    /**
     * Creates and returns a new UserBean object.
     */
    @Override
    public UserBean getBean() {

        log.debug("getBean() method called");

        return new UserBean();
    }
}
