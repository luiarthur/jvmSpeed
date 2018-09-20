# Julia v1.0.0
using JLD2, FileIO

y = [ randn(50000, 32) for i in 1:10 ]
@save "data/dat.jld2" y

