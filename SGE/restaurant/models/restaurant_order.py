from odoo import models, fields, api

class RestaurantOrder(models.Model):
    _name = 'restaurant.orders'
    _description = 'Restaurant orders'

    name = fields.Many2one('restaurant.tables',string="Table", required=True)
    menu_items = fields.Many2one('restaurant.menu',string="Menu Items", required=True)
    total_price = fields.Float(string="Total Price", compute="_calculate_price",store=True,default=0.00)

    @api.depends("menu_items")
    def _calculate_price(self):
        for rec in self:
            rec.total_price = sum(rec.menu_items.mapped('price'))