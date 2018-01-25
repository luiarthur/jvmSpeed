import numpy as np

N=50000
J=32
M=10

X = [np.random.randn(N, J)] * M
np.save('data/dat', X)
