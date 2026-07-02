from django.urls import path, include
from rest_framework import routers

from .views import PostModelViewSet

app_name = 'posts'

router_posts = routers.DefaultRouter()
router_posts.register('', PostModelViewSet)
                     
urlpatterns = [
    path('', include(router_posts.urls)),
]