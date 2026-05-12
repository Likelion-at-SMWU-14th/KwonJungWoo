from django.contrib import admin
from .models import User

# Register your models here.
# admin.site.register(User)

@admin.register(User)
class UserModelAdmiin(admin.ModelAdmin):
    search_fields = ['nickname']
    list_display = ['nickname', 'post_count']
    actions = ['delete']

def delete(self, request, queryset):
    for item in queryset:
        item.username = '삭제된 사용자입니다.'
        item.save()