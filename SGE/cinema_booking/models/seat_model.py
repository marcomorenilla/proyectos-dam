from odoo import models, fields

class CinemaSeat(models.Model):
    _name = 'cinema.seat'
    _description = 'Cinema Seat'

    name = fields.Char(string="Seat Number", required=True)
    row = fields.Char(string="Row")
    seat_type = fields.Selection([
        ('regular', 'Regular'),
        ('vip', 'VIP'),
        ('disabled', 'Accessible')
        ],string="Seat Type", default='regular')
    available = fields.Boolean(string="Available", default=True)
