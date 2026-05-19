from django import forms
from .models import Post

class PostBasedForm(forms.Form):
    image = forms.ImageField()

class PostModelForm(forms.ModelForm):
    class Meta:
        model = Post
        fields = '__all__'