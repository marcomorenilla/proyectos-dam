from odoo import models, fields

class ClinicPatient(models.Model):
    _name = 'clinic.patient'
    _description = 'Patient'

    name = fields.Char(string="Patient Name", required=True)
    age = fields.Integer(string="Age")
    gender = fields.Selection([('male', 'Male'), ('female', 'Female')], string="Gender")
    phone = fields.Char(string="Phone Number")
    email = fields.Char(string="Email")
    appointment_ids = fields.One2many('clinic.appointment', 'patient_id', string="Appointments")
