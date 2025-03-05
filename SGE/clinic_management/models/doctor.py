from odoo import models, fields

class ClinicDoctor(models.Model):
    _name = 'clinic.doctor'
    _description = 'Doctor'

    name = fields.Char(string="Doctor Name", required=True)
    specialization = fields.Char(string="Specialization")
    phone = fields.Char(string="Phone Number")
    email = fields.Char(string="Email")
    appointment_ids = fields.One2many('clinic.appointment', 'doctor_id', string="Appointments")
