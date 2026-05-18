from django.db import models

# Create your models here.
class Books(models.Model):
    name = models.TextField(verbose_name='제목')
    author = models.TextField(verbose_name='저자')
    rate = models.PositiveIntegerField(verbose_name='평점')
    text = models.TextField(verbose_name='독후감')