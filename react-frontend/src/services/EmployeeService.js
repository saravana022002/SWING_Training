import {request} from '../axios_helper';

const EMPLOYEE_API_URL = "/api/v1/employees";

class EmployeeService {

    getEmployees(){
        return request("GET", EMPLOYEE_API_URL, {});
    }

    createEmployee(employee){
        return request("POST", EMPLOYEE_API_URL, employee);
    }

    getEmployeeById(employeeId){
        return request("GET", EMPLOYEE_API_URL + '/' + employeeId, {});
    }

    updateEmployee(employee, employeeId){
        return request("PUT", EMPLOYEE_API_URL + '/' + employeeId, employee);
    }

    deleteEmployee(employeeId){
        return request("DELETE", EMPLOYEE_API_URL + '/' + employeeId, {});
    }
}

export default new EmployeeService()