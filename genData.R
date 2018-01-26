X = matrix(rnorm(50000 * 32), 50000, 32)
#save(X, file='data/dat.RData')

y = rep(as.list(X), 10)
save(y, file='data/dat.RData')

write.table(X, file='data/dat.txt', quote=FALSE, col.names=FALSE, row.names=FALSE)
