from odoo import models, fields

class BusinessTrainer(models.Model):
    _name = 'business.trainers'
    _description = 'Business Trainers'

    name = fields.Char(string="Trainer", required=True)
    company = fields.Char(string="Company")
    