from django.shortcuts import render
from rest_framework import generics
from rest_framework.viewsets import ModelViewSet

from django.contrib.auth import get_user_model
from .serializers import UserCreateSerializer, UserLoginSerializer

User = get_user_model()

class UserModelViewSet(ModelViewSet):
    queryset = User.objects.all()

    def get_serializer_class(self):
        if self.action == 'create':
            return UserCreateSerializer