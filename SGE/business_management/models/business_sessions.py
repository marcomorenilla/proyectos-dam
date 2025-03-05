from odoo import models, fields, api

class BusinessSessions(models.Model):
    _name = 'business.sessions'
    _description = 'Business sessions'

    name = fields.Char(string="Training Name", required=True)
    course = fields.Many2one('business.courses',string="Course", required=True)
    trainer = fields.Many2one('business.trainers',string="Trainer")
    training_date=fields.Datetime(string="Training Date", required=True)
    duration=fields.Float(string='Duration (hours)', default=0.00)
    status=fields.Selection([
        ('draft','Draft'),
        ('confirmed','Confirmed'),
        ('done','Done')
    ], string='Status', default='draft')
    employee_id=fields.One2many('business.employees','name',string="Attendees")

    