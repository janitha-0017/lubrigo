#!/usr/bin/env python
# coding: utf-8

# In[3]:


from scipy import stats
import matplotlib.pyplot as plt

x=[0,1000,2000,3000,4000,5000,6000]
y=[0.625,0.5,0.375,0.1875,0.1,0.0125,0]
slope, intercept, r, p, std_err = stats.linregress(x, y)

def myfunc(x):
  return slope * x + intercept

mymodel = list(map(myfunc, x))
plt.scatter(x, y)
plt.plot(x, mymodel)
plt.show() 

quality = myfunc(0.2)

print(quality)


# In[ ]:

#csv file reader added 
from scipy import stats
import matplotlib.pyplot as plt
import pandas as pd
df=pd.read_csv("https://readinglubrigo1.s3.us-east-2.amazonaws.com/Radings.csv")
x=[df["IRatio"]]
y=[df["Milage"]]
slope, intercept, r, p, std_err = stats.linregress(x, y)

def myfunc(x):
  return slope * x + intercept

mymodel = list(map(myfunc, x))
plt.scatter(x, y)
plt.plot(x, mymodel)
plt.show() 

milage = myfunc(0.2)

print(milage)




