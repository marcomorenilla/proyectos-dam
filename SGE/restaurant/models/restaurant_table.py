from odoo import models, fields

class RestaurantTable(models.Model):
    _name = 'restaurant.tables'
    _description = 'Restaurant tables'

    name = fields.Char(string="Table", required=True)
    capacity=fields.Integer(string="Capacity")
