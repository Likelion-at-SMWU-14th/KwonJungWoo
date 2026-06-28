from rest_framework.viewsets import ModelViewSet

from django.contrib.auth.models import User
from .serializers import UserModelSerializer

class UserModelViewSet(ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserModelSerializer

