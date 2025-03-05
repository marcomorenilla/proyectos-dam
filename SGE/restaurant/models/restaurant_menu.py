from odoo import models, fields

class RestaurantMenu(models.Model):
    _name = 'restaurant.menu'
    _description = 'Restaurant menu'

    name = fields.Char(string="Dish Name", required=True)
    category = fields.Char(string="Category", required=True)
    price = fields.Float(string="Price", default=0.00)
