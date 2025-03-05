from odoo import models, fields, api

class BusinessBonus(models.Model):
    _name = 'business.bonus'
    _description = 'Business bonus'

    name = fields.Many2one('business.employees',string="Employee", required=True)
    monthly_salary=fields.Float(string='Monthly Salary', compute="_get_salary", store=True)
    bonus=fields.Float(string="Bonus(%)", default=0.00, required=True)
    final_bonus = fields.Float(string="Final Bonus", compute="_get_bonus", store=True)
    remarks = fields.Text(string="Remarks")

    @api.depends("name")
    def _get_salary(self):
        for record in self:
            record.monthly_salary = record.name.salary
    
    @api.depends("bonus")
    def _get_bonus(self):
        for record in self:
            record.final_bonus = record.monthly_salary * record.bonus / 100