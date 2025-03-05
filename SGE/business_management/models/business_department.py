from odoo import models, fields, api

class BussinessDepartment(models.Model):
    _name = 'business.departments'
    _description = 'Business Department'

    name = fields.Char(string="Department Name", required=True)
    average_salary = fields.Float(string="Average Salary", compute="_compute_average_salary", store=True)
    employee_id= fields.One2many('business.employees','department_id', string="Employees")

    @api.depends("employee_id")
    def _compute_average_salary(self):
        for rec in self:
            employees = rec.employee_id  # Lista de empleados en el departamento
            if employees:
                total_salary = sum(employees.mapped('salary'))  # Sumar todos los salarios
                rec.average_salary = total_salary / len(employees)  # Calcular el promedio
            else:
                rec.average_salary = 0.0  # Si no hay empleados, el promedio es 0

    