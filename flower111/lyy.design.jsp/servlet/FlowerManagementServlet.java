package servlet;

import Dao.Flower;
import service.FlowerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FlowerManagementServlet")
public class FlowerManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前页码，默认为1，如果前端有传递页码参数则进行更新
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam!= null &&!pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // 如果参数格式转换失败，保持默认页码1
            }
        }

        // 每页显示的鲜花数量，可根据实际需求调整
        int pageSize = 10;

        // 调用FlowerService获取鲜花列表数据
        FlowerService flowerService = new FlowerService();
        List<Flower> flowerList = flowerService.getAllFlowers(page, pageSize);

        // 获取鲜花总数，用于分页相关功能展示（比如计算总页数等）
        int totalFlowers = flowerService.getTotalFlowers();

        // 将鲜花列表数据和鲜花总数设置到请求作用域，以便在flowerManagement.jsp页面中获取使用
        request.setAttribute("flowerList", flowerList);
        request.setAttribute("totalFlowers", totalFlowers);
        request.setAttribute("currentPage", page);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalFlowers / pageSize);
        request.setAttribute("totalPages", totalPages);

        // 转发请求到flowerManagement.jsp页面进行展示
        request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单提交的操作类型，这里可以根据实际情况扩展更多操作类型判断
        String operation = request.getParameter("operation");
        if ("addFlower".equals(operation)) {
            // 如果是添加鲜花操作，调用添加鲜花逻辑处理
            handleAddFlower(request, response);
        } else {
            // 对于其他未识别的操作或者没有指定操作类型的情况，可以进行相应处理，比如提示错误信息等
            request.setAttribute("errorMessage", "不支持的操作类型");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }

    private void handleAddFlower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端表单获取鲜花相关信息
        String flowerName = request.getParameter("flower_name");
        String flowerPrice = request.getParameter("flower_price");
        String flowerContent = request.getParameter("flower_content");
        String stock = request.getParameter("stock");

        Flower newFlower = new Flower();
        try {
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

        // 调用FlowerService的方法来处理添加鲜花的业务逻辑
        FlowerService flowerService = new FlowerService();
        boolean success = flowerService.addFlower(newFlower);

        if (success) {
            // 如果添加成功，重定向到当前FlowerManagementServlet（刷新页面，同时可以获取最新的鲜花列表数据展示）
            // 也可以添加提示信息到请求作用域，用于在页面显示添加成功的提示，这里重定向方式刷新页面暂不设置提示信息
            response.sendRedirect("FlowerManagementServlet");
        } else {
            // 如果添加失败，添加错误提示信息到请求作用域，用于在页面显示添加失败的提示
            request.setAttribute("errorMessage", "鲜花添加失败，请检查输入信息或联系管理员。");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }
}