from odoo import models, fields

class TodoTask(models.Model):
    _name = 'todo.task'
    _description = 'Todo App'

    name = fields.Char(string="task", required=True)
    description = fields.Char(string="description")
    is_done = fields.Boolean(string="is done")
    
