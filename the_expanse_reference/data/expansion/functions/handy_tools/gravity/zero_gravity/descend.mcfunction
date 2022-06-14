effect give @s slow_falling 1 0 true
effect clear @s levitation
execute rotated ~ 0 positioned ~ ~1.5 ~ run particle poof ^ ^ ^-0.5 0.1 0.1 0.1 0 2
tag @s[tag=rising] remove rising
tag @s[tag=falling] remove falling
#playsound entity.cat.hiss master @s ~ ~ ~ 0.1 2 1 