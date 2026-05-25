from django import forms
from .models import Post, Comment

class PostBasedForm(forms.Form):
    image = forms.ImageField()

class PostModelForm(forms.ModelForm):
    class Meta:
        model = Post
        fields = '__all__'

class CommentModelForm(forms.ModelForm):
    class Meta:
        model = Comment
        fields = '__all__'
