from django.db import models
from django.urls import reverse
from .fields import ThumbnailImageField
from django.contrib.auth.models import User


# Create your models here.
class Album(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField('One Line Description', max_length=100, blank=True)
    owner = models.ForeignKey(User, null=True, on_delete=models.CASCADE)

    class Meta:
        ordering = ['name']

    def get_absolute_url(self):
        return reverse('photoApp:album_detail', args=(self.id, ))


class Photo(models.Model):
    album = models.ForeignKey('Album', on_delete=models.CASCADE)
    title = models.CharField(max_length=50)
    image = ThumbnailImageField(upload_to='photo/%Y/%m')
    description = models.TextField('Photo Description', blank=True)
    upload_date = models.DateTimeField('Upload Date', auto_now_add=True)
    owner = models.ForeignKey(User, null=True, on_delete=models.CASCADE)

    class Meta:
        ordering = ['title']

    def __str__(self):
        return self.title

    def get_absolute_url(self):
        return reverse('photoApp:photo_detail', args=(self.id, ))
