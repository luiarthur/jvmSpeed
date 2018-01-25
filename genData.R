X = matrix(rnorm(50000 * 32), 50000, 32)
#save(X, file='data/dat.RData')

y = rep(as.list(X), 10)
save(y, file='data/dat.RData')
