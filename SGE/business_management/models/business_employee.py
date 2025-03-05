from odoo import models, fields

class BussinessEmployee(models.Model):
    _name = 'business.employees'
    _description = 'Business Employee'

    name = fields.Char(string="Employee Name", required=True)
    salary = fields.Float(string="Salary")
    department_id = fields.Many2one('business.departments',string="Department")