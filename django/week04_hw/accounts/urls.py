from django.urls import path, include
from rest_framework import routers

from .views import UserModelViewSet

app_name = 'accounts'

router = routers.DefaultRouter()
router.register('', UserModelViewSet, basename='accounts')
urlpatterns = [
    path('', include(router.urls)),
]