from keras.models import Sequential
from keras.layers import Dense

import numpy as np

x_train = np.array([x for x in range(1, 11)])
y_train = np.array([x for x in range(1, 11)])
x_test = np.array([x for x in range(11, 21)])
y_test = np.array([x for x in range(11, 21)])
x_predict = np.array([x for x in range(21, 26)])

model = Sequential()
# model.add(Dense(100, input_dim=1, activation='relu'))
model.add(Dense(100, input_shape=(1, ), activation='relu'))
model.add(Dense(230))
model.add(Dense(170))
model.add(Dense(320))
model.add(Dense(250))
model.add(Dense(190))
model.add(Dense(210))
model.add(Dense(1))

model.summary()

model.compile(loss='mse', optimizer='adam', metrics=['accuracy'])
model.fit(x_train, y_train, epochs=250, batch_size=16) # default batch_size = 32

loss, acc = model.evaluate(x_test, y_test) # mean squared error
print("acc :", acc)
print("loss :", loss)

y_predict = model.predict(x_predict)
print(y_predict)