from odoo import models, fields, api

class BusinessCourses(models.Model):
    _name = 'business.courses'
    _description = 'Business courses'

    name = fields.Char(string="Course Name", required=True)
    description = fields.Char(string="Description")
    session_id =fields.One2many('business.sessions','name', string="Sessions")
    
