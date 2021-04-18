from scipy import stats
import matplotlib.pyplot as plt
import pandas as pd

from os.path import dirname, join
filename = join(dirname(__file__), "Radings.csv")


df=pd.read_csv(filename)
X = df.iloc[:, 0:1].values
y = df.iloc[:, 1].values

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)

from sklearn.linear_model import LinearRegression
lin_reg = LinearRegression()
lin_reg.fit(X, y)

# Fitting Polynomial Regression to the dataset
from sklearn.preprocessing import PolynomialFeatures
poly_reg = PolynomialFeatures(degree=4)
X_poly = poly_reg.fit_transform(X)
pol_reg = LinearRegression()
pol_reg.fit(X_poly, y)

# Visualizing the Polymonial Regression results
def viz_polymonial():
  plt.scatter(X, y, color='red')
  plt.plot(X, pol_reg.predict(poly_reg.fit_transform(X)), color='blue')
  plt.title('Lubrigo Engine oil quelity tester')
  plt.xlabel('Intensity Ratio')
  plt.ylabel('Milage')
  plt.show()
  return ("Hello")

viz_polymonial()

# Predicting a new result with Polymonial Regression
pol_reg.predict(poly_reg.fit_transform([[0.2]]))




