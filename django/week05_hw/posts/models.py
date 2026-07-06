from django.db import models
from django.contrib.auth import get_user_model

User = get_user_model()

# Create your models here.
class Post(models.Model):
    writer = models.ForeignKey(User, on_delete=models.CASCADE, verbose_name='작성자',null=True, blank=True)
    content = models.TextField(verbose_name='내용')
    created_at = models.DateTimeField(verbose_name='작성일시',auto_now_add=True)
    view_count = models.PositiveIntegerField(verbose_name='조회수', default=0)