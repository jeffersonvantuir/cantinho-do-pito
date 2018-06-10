package Controller;

import DAO.CityDAO;
import DAO.ClientDAO;
import DAO.StateDAO;
import Helpers.Helper;
import Model.Client;
import Model.State;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jefferson
 */
public class ClientsController extends HttpServlet {
    
    private List listStates;
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        ClientDAO clientDAO = new ClientDAO();
        CityDAO cityDAO = new CityDAO();
        StateDAO stateDAO = new StateDAO();
        Helper helper = new Helper();
        HttpSession session = request.getSession();

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "request-add":
                listStates = stateDAO.list();
                
                request.setAttribute("listStates", listStates);
                rd = request.getRequestDispatcher("Clients/add.jsp");
                rd.forward(request, response);
                break;
                
            case "add":
                if (!clientDAO.cpfAlready(0, request.getParameter("cpf"))) {
                    if (!clientDAO.emailAlready(0, request.getParameter("email"))) {
                        if (clientDAO.passwordMathConfirmPassword(request.getParameter("password"), request.getParameter("confirm_password"))) {
                            Client client = new Client();
                            client.setName(request.getParameter("name"));
                            client.setEmail(request.getParameter("email"));
                            client.setCpf(clientDAO.removeMaskCPF(request.getParameter("cpf")));
                            client.setCellphone(request.getParameter("cellphone"));
                            client.setPassword(helper.md5(request.getParameter("password")));
                            try {
                                client.setBirthday(helper.formatterDateDB(request.getParameter("birthday")));
                            } catch (ParseException ex) {
                                Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            client.setAddress(request.getParameter("address"));
                            client.setZipcode(request.getParameter("zipcode"));
                            client.setDistrict(request.getParameter("district"));
                            client.setComplement(request.getParameter("complement"));

                            if (!request.getParameter("birthday").equals("")) {
                                client.setBirthday(request.getParameter("birthday"));
                            } else {
                                client.setBirthday(null);
                            }

                            if (!request.getParameter("home_number").equals("")) {
                                client.setHomeNumber(Integer.parseInt(request.getParameter("home_number")));
                            } else {
                                client.setHomeNumber(0);
                            }

                            client.setCityId(Integer.parseInt(request.getParameter("city")));

                            if (clientDAO.add(client)) {
                                request.setAttribute("success", "Cadastro realizado com sucesso.");
                                rd = request.getRequestDispatcher("clients?action=index");
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("error", "Falha ao cadastrar novo cliente. Tente novamente.");
                                rd = request.getRequestDispatcher("clients?action=index");
                                rd.forward(request, response);
                            }

                        } else {
                            request.setAttribute("error", "Senhas não correspondem.");
                            rd = request.getRequestDispatcher("clients?action=request-add");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("error", "E-mail já está sendo usado.");
                        rd = request.getRequestDispatcher("clients?action=request-add");
                        rd.forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "CPF já está sendo usado.");
                    rd = request.getRequestDispatcher("clients?action=request-add");
                    rd.forward(request, response);
                }
                break;
                
            case "load-cities":
                State state = new State();
                state.setId(Integer.parseInt(request.getParameter("state_id")));

                List listCities = cityDAO.list(state);
                request.setAttribute("listCities", listCities);
                rd = request.getRequestDispatcher("Clients/load_cities.jsp");
                rd.forward(request, response);
                break;
                
            case "request-edit":
                listStates = stateDAO.list();
                
                Client client = clientDAO.view(Integer.parseInt(request.getParameter("id")));
                State stateSelect = new State();
                stateSelect.setId(cityDAO.getStateIdByCityId(client.getCityId()));

                request.setAttribute("stateSelect", stateSelect);
                request.setAttribute("listStates", listStates);
                request.setAttribute("client", client);
                rd = request.getRequestDispatcher("Clients/edit.jsp");
                rd.forward(request, response);
                break;
                
            case "edit":
                
                if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
                    rd = request.getRequestDispatcher("clients?action=null");
                    rd.forward(request, response);
                }
                
                int id = Integer.parseInt(String.valueOf(session.getAttribute("id")));
                
                if (!clientDAO.cpfAlready(id, request.getParameter("cpf"))) {
                    if (!clientDAO.emailAlready(id, request.getParameter("email"))) {
                        client = new Client();
                        client.setId(id);
                        client.setName(request.getParameter("name"));
                        client.setEmail(request.getParameter("email"));
                        client.setCpf(clientDAO.removeMaskCPF(request.getParameter("cpf")));
                        client.setCellphone(request.getParameter("cellphone"));
                        client.setBirthday(request.getParameter("birthday"));
                        client.setAddress(request.getParameter("address"));
                        client.setZipcode(request.getParameter("zipcode"));
                        client.setDistrict(request.getParameter("district"));
                        client.setComplement(request.getParameter("complement"));

                        if (!request.getParameter("birthday").equals("")) {
                            client.setBirthday(request.getParameter("birthday"));
                        } else {
                            client.setBirthday(null);
                        }

                        if (!request.getParameter("home_number").equals("")) {
                            client.setHomeNumber(Integer.parseInt(request.getParameter("home_number")));
                        } else {
                            client.setHomeNumber(0);
                        }

                        client.setCityId(Integer.parseInt(request.getParameter("city")));

                        if (clientDAO.edit(client)) {
                            session.setAttribute("id", null);
                            request.setAttribute("success", "Cliente editado com sucesso.");
                            rd = request.getRequestDispatcher("clients?action=null");
                            rd.forward(request, response);
                        } else {
                            session.setAttribute("id", null);
                            request.setAttribute("error", "Falha ao editar cliente. Tente novamente.");
                            rd = request.getRequestDispatcher("clients?action=null");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("error", "E-mail já está sendo usado.");
                        rd = request.getRequestDispatcher("clients?action=request-edit");
                        rd.forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "CPF já está sendo usado.");
                    rd = request.getRequestDispatcher("clients?action=request-edit");
                    rd.forward(request, response);
                }
                break;
            
            case "delete":
                client = clientDAO.view(Integer.parseInt(request.getParameter("id")));
                
                if (clientDAO.delete(client)) {
                    request.setAttribute("success", "Cliente excluído com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao excluir cliente. Tente novamente.");
                }
                rd = request.getRequestDispatcher("clients?action=null");
                rd.forward(request, response);
                break;
                
            default:
                List listClients = clientDAO.index();
                
                request.setAttribute("listClients", listClients);
                rd = request.getRequestDispatcher("Clients/index.jsp");
                rd.forward(request, response);
                break;
        }

       
        
        /*
        List listStates = stateDAO.list();
        request.setAttribute("listStates", listStates);
        rd = request.getRequestDispatcher("add.jsp");
        rd.forward(request, response);
        if (action.equalsIgnoreCase("list")) {
            List listClients = clientDAO.list();

            request.setAttribute("listClients", listClients);

            rd = request.getRequestDispatcher("showAllClients.jsp");
            rd.forward(request, response);
        }*/
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
