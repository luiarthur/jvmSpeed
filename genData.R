#X = matrix(rnorm(50000 * 32), 50000, 32)
#X = matrix(sample(1:10, 50000 * 32, replace=TRUE), 50000, 32)
#save(X, file='data/dat.RData')

### IntegerMatrix is cheaper by an order of magnitude
#y = lapply(as.list(1:10), function(i)
#           matrix(sample(1:10, 50000 * 32, replace=TRUE), 50000, 32))

y = lapply(as.list(1:10), function(i)
           matrix(rnorm(50000 * 32), 50000, 32))
saveRDS(y, file='data/dat.rds')

#write.table(X, file='data/dat.txt', quote=FALSE, col.names=FALSE, row.names=FALSE)
