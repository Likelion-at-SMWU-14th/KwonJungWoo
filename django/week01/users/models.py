from django.db import models

# Create your models here.

class User(models.Model):
    profile = models.ImageField(verbose_name='프로필사진')
    nickname = models.CharField(verbose_name='닉네임')
    username = models.CharField(verbose_name='사용자이름')
    post_count = models.PositiveIntegerField(verbose_name='게시물개수')
    following_count = models.PositiveIntegerField(verbose_name='팔로잉수')
    follower_count = models.PositiveIntegerField(verbose_name='팔로워수')