package servlet;

import Dao.Flower;
import Dao.FlowerDAO;
import Dao.impl.FlowerDAOImpl;
import service.FlowerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditFlowerServlet")
public class EditFlowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要编辑的鲜花ID
        int flowerId = Integer.parseInt(request.getParameter("flowerId"));

        // 根据鲜花ID查询鲜花信息
        FlowerDAO flowerDAO = new FlowerDAOImpl();
        Flower flower = flowerDAO.getFlowerById(flowerId);

        // 将鲜花信息设置到请求属性中
        request.setAttribute("flower", flower);

        // 转发到编辑鲜花页面
        request.getRequestDispatcher("editFlower.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flowerId=request.getParameter("flowerId");
        String flowerName = request.getParameter("flowerName");
        String flowerPrice = request.getParameter("flowerPrice");
        String flowerContent = request.getParameter("flowerContent");
        String stock = request.getParameter("stock");

        Flower newFlower = new Flower();
        try {
            newFlower.setFlower_id(Integer.parseInt(flowerId));
            newFlower.setFlower_name(flowerName);
            newFlower.setFlower_price(Double.parseDouble(flowerPrice));
            newFlower.setFlower_content(flowerContent);
            newFlower.setStock(Integer.parseInt(stock));
        } catch (NumberFormatException e) {
            // 如果参数转换整数出现异常，说明输入的数据格式有误，设置错误提示信息并转发到订单管理页面展示错误
            request.setAttribute("errorMessage", "鲜花信息输入格式有误，请检查输入的数字类型字段是否正确。");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
            return;
        }

        FlowerDAO flowerDAO = new FlowerDAOImpl();
        boolean success = flowerDAO.updateFlower(newFlower);

        if (success) {
            // 如果添加成功，重定向到订单管理页面，并添加提示信息到请求作用域，用于在页面显示添加成功的提示
            request.setAttribute("message", "鲜花修改成功！");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        } else {
            // 如果添加失败，重定向到订单管理页面，并添加错误提示信息到请求作用域，用于在页面显示添加失败的提示
            request.setAttribute("errorMessage", "订单添加失败，请检查输入信息或联系管理员。");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }
}