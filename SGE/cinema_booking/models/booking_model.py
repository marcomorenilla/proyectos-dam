from odoo import models, fields

class CinemaBooking(models.Model):
    _name = 'cinema.booking'
    _description = 'Cinema Booking'

    name = fields.Char(string="Customer Name", required=True)
    seat_id = fields.Many2one('cinema.seat',string="Seat", required=True)
    booking_date = fields.Datetime(string="Booking Date")
