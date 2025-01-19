package servlet;

import Dao.Address;
import service.AddressService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String mobile = request.getParameter("mobile");
        String notice = request.getParameter("notice");

        Address addressObj = new Address();
        addressObj.setUser_id(userId);
        addressObj.setAddress(address);
        addressObj.setTel(tel);
        addressObj.setMobile(mobile);
        addressObj.setNotice(notice);

        AddressService addressService = new AddressService();
        addressService.addAddress(addressObj);

        response.sendRedirect("addressList.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}