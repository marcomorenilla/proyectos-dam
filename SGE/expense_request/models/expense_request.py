from odoo import models, fields

class ExpenseRequest(models.Model):
    _name = 'expense.request'
    _description = 'Expense Request'

    name = fields.Char(string="Request Name", required=True)
    employee = fields.Many2one('res.users', string="Employee", required = True)
    amount = fields.Float(string="Amount", default= 0.00)
    description = fields.Text(string="Description")
    status =fields.Selection([
        ('draft', 'Draft'),
        ('billed','Billed'),
        ('pending', 'Pending')
    ], string = 'Status', default='draft')
    manager = fields.Char(string="Manager")